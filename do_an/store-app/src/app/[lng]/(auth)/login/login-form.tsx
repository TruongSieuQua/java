"use client";

import { Button } from "@/components/ui/button";
import {
  FormControl,
  FormField,
  FormLabel,
  FormMessage,
  Form,
  FormSubmit,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Text } from "@/components/ui/typography";
import { Controller } from "react-hook-form";
import { useForm } from "@/utils/useForm";
import { LoginData, LoginSchema } from "@/schema";
import { BiSolidJoystickButton } from "react-icons/bi";
import { PageProps } from "@/types";
import { useTranslation } from "@/i18n/client";
import NextLink from "next/link";
import { LinkText } from "@/components/ui/link";

interface LoginFormProps extends PageProps {}

export default function LoginForm({ params: { lng } }: LoginFormProps) {
  const { t } = useTranslation(lng, ["login-page", "errors"]);
  const {
    handleSubmit,
    control,
    formState: { errors },
  } = useForm<LoginData>({
    schema: LoginSchema,
    defaultValues: {
      email: "",
      password: "",
    },
    mode: "onChange",
  });

  function onSubmit(data: LoginData) {
    //call action
  }

  return (
    <Form onSubmit={handleSubmit(onSubmit)} className="flex flex-col gap-3">
      <Controller
        control={control}
        name={"email"}
        render={({ field, fieldState: { isDirty, error } }) => (
          <FormField>
            <FormLabel htmlFor="email">{t("email")}</FormLabel>
            <FormControl>
              <Input
								id="email"
                type="email"
                {...field}
                color={!isDirty ? "primary" : !error ? "success" : "error"}
              />
            </FormControl>
            {errors.email?.message && (
              <FormMessage className="mt-1 text-error">
                {t(errors.email.message, { ns: "errors" })}
              </FormMessage>
            )}
          </FormField>
        )}
      />
      <Controller
        control={control}
        name="password"
        render={({ field, fieldState: { isDirty, error } }) => {
          return (
            <FormField>
              <FormLabel htmlFor="password">{t("password")}</FormLabel>
              <FormControl>
                <Input
									id="password"
                  type="password"
                  {...field}
                  color={!isDirty ? "primary" : !error ? "success" : "error"}
                />
              </FormControl>
              {errors.password?.message && (
                <FormMessage className="mt-1 text-error">
                  {t(errors.password.message, { ns: "errors" })}
                </FormMessage>
              )}
            </FormField>
          );
        }}
      />

      <div className="mb-4 flex justify-between text-sm">
        <div>
          <label className="label cursor-pointer space-x-2">
            <input type="checkbox" defaultChecked className="checkbox" />
            <span className="label-text whitespace-nowrap">Remember me</span>
          </label>
        </div>
        <NextLink href={`/${lng}/forgot-password`}>
          <LinkText>{t("forgot-password")}</LinkText>
        </NextLink>
      </div>

      <FormSubmit asChild>
        <Button type="submit" className="btn-primary">
          <BiSolidJoystickButton />
          <Text>{t("submit")}</Text>
        </Button>
      </FormSubmit>
    </Form>
  );
}
