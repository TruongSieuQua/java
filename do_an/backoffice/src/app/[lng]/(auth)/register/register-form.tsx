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
  InputTextLabel,
  InputIcon,
} from "@/components/ui/form";
import { Text } from "@/components/ui/typography";
import { Controller } from "react-hook-form";
import { useForm } from "@/utils/useForm";
import { RegisterData, RegisterSchema } from "@/schema";
import { BiSolidJoystickButton } from "react-icons/bi";
import { PageProps } from "@/interface";
import { useTranslation } from "@/i18n/client";
import { TfiUser } from "react-icons/tfi";
import { SiGmail } from "react-icons/si";
import { RiLockPasswordLine } from "react-icons/ri";

interface RegisterFormProps extends PageProps {}

export default function LoginForm({ params: { lng } }: RegisterFormProps) {
  const { t } = useTranslation(lng, ["register-page", "errors"]);
  const {
    handleSubmit,
    control,
    formState: { errors },
  } = useForm<RegisterData>({
		schema: RegisterSchema,
    defaultValues: {
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      confirmedPassword: "",
    },
    mode: "onChange",
  });

  function onSubmit(data: RegisterData) {
    //call action
  }

  return (
    <Form onSubmit={handleSubmit(onSubmit)} className="flex flex-col gap-3">
      <div className="grid grid-cols-2 gap-4">
        <Controller
          control={control}
          name={"firstName"}
          render={({ field, fieldState: { isDirty, error } }) => (
            <FormField>
              <FormControl>
                <InputIcon
                  {...field}
                  icon={<TfiUser />}
                  placeholder={t("first_name")}
                  color={!isDirty ? "primary" : !error ? "success" : "error"}
                />
              </FormControl>
              {errors.firstName?.message && (
                <FormMessage className="mt-1 text-error">
                  {t(errors.firstName.message, { ns: "errors" })}
                </FormMessage>
              )}
            </FormField>
          )}
        />
        <Controller
          control={control}
          name={"lastName"}
          render={({ field, fieldState: { isDirty, error } }) => (
            <FormField>
              <FormControl>
                <InputIcon
                  {...field}
                  icon={<TfiUser />}
                  placeholder={t("last_name")}
                  color={!isDirty ? "primary" : !error ? "success" : "error"}
                />
              </FormControl>
              {errors.lastName?.message && (
                <FormMessage className="mt-1 text-error">
                  {t(errors.lastName.message, { ns: "errors" })}
                </FormMessage>
              )}
            </FormField>
          )}
        />
      </div>
      <Controller
        control={control}
        name={"email"}
        render={({ field, fieldState: { isDirty, error } }) => (
          <FormField>
            <FormControl>
              <InputIcon
                {...field}
                type="email"
                icon={<SiGmail />}
                placeholder={t("email")}
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
              <FormControl>
                <InputIcon
                  {...field}
                  type="password"
                  icon={<RiLockPasswordLine />}
                  placeholder={t("password")}
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

      <Controller
        control={control}
        name="confirmedPassword"
        render={({ field, fieldState: { isDirty, error } }) => {
          return (
            <FormField>
              <FormControl>
                <InputIcon
                  {...field}
                  type="password"
                  icon={<RiLockPasswordLine />}
                  placeholder={t("confirmed_password")}
                  color={!isDirty ? "primary" : !error ? "success" : "error"}
                />
              </FormControl>
              {errors.confirmedPassword?.message && (
                <FormMessage className="mt-1 text-error">
                  {t(errors.confirmedPassword.message, { ns: "errors" })}
                </FormMessage>
              )}
            </FormField>
          );
        }}
      />

      <FormSubmit asChild>
        <Button type="submit" className="btn-primary">
          <BiSolidJoystickButton />
          <Text>{t("submit")}</Text>
        </Button>
      </FormSubmit>
    </Form>
  );
}
