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
import { Controller, useForm } from "react-hook-form";
import { parse } from "valibot";
import { valibotResolver } from '@hookform/resolvers/valibot';
import {LoginData, LoginSchema} from "@/schema";
import { BiSolidJoystickButton } from "react-icons/bi";
import { useTranslation } from "react-i18next";



export default function LoginForm(){
	const {i18n} = useTranslation();
	const t = i18n.t;
	// const lang = i18n.language;

	const { handleSubmit, control,  formState: { errors } } = useForm<LoginData>({
		resolver: valibotResolver(LoginSchema),
		defaultValues: {
			email: "",
			password: ""
		}
	})

	function onSubmit(data: LoginData){
		console.log(data);
	}

	return <Form onSubmit={handleSubmit(onSubmit)}>
	<Controller
		control={control}
		name={"email"}
		render={({ field, fieldState,  }) => (
			<FormField name="email">
				<FormLabel>{t("email")}</FormLabel>
				<FormControl>
					<Input type="email" {...field} color={fieldState.error?"error":"default"} required/>
				</FormControl>
				{errors.email?.message
					&& <FormMessage color="danger">{t(errors.email.message)}</FormMessage>}
			</FormField>
		)}
	/>
	<Controller
		control={control}
		name="password"
		render={({ field, fieldState }) => {
			return <FormField name="password">
				<FormLabel>{t("password")}</FormLabel>
				<FormControl>
					<Input type="password" {...field} color={fieldState.error?"error":"default"} required/>
				</FormControl>
				{errors.password?.message
					&& <FormMessage color="danger">{t(errors.password.message)}</FormMessage>}
			</FormField>
		}}
	/>
	<FormSubmit asChild>
		<Button type="submit">
			<BiSolidJoystickButton />
			{t("submit")}
		</Button>
	</FormSubmit>
</Form>
}
