"use client";

import { Button } from "@/components/ui/button";
import {
  FormControl,
  FormField,
  FormLabel,
  FormMessage,
  Form,
  FormSubmit,
  Input,
} from "@/components/ui/form";
import { Text } from "@/components/ui/typography";
import { Controller, useForm } from "react-hook-form";
import { valibotResolver } from "@hookform/resolvers/valibot";
import { LoginData, LoginSchema } from "@/schema";
import { BiSolidJoystickButton } from "react-icons/bi";
import { PageProps } from "@/interface";
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
    resolver: valibotResolver(LoginSchema),
    defaultValues: {
      email: "",
      password: "",
    },
		mode:'onChange'
  });

  function onSubmit(data: LoginData) {
    //call action
  }

  return (
    <Form onSubmit={handleSubmit(onSubmit)} className="flex flex-col gap-3">
      <Controller
        control={control}
        name={"email"}
        render={({ field, fieldState:{isDirty, error} }) => (
          <FormField name="email">
            <FormLabel>{t("email")}</FormLabel>
            <FormControl>
              <Input
                type="email"
                {...field}
                color={!isDirty ? "primary" : !error ? "success" : "error"}
              />
            </FormControl>
            {errors.email?.message && (
              <FormMessage className="text-error mt-1">
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
            <FormField name="password">
              <FormLabel>{t("password")}</FormLabel>
              <FormControl>
                <Input
                  type="password"
                  {...field}
									color={!isDirty ? "primary" : !error ? "success" : "error"}
                />
              </FormControl>
              {errors.password?.message && (
                <FormMessage className="text-error mt-1">
                  {t(errors.password.message, { ns: "errors" })}
                </FormMessage>
              )}
            </FormField>
          );
        }}
      />

      <div className="flex justify-between text-sm mb-4">
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
