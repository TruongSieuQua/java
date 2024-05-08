import {toTrimmed, endsWith, email, minLength, maxLength, object, Output, string, custom } from 'valibot';
import { EmailSchema, NameSchema, PasswordSchema } from './schema';

export const LoginSchema = object({
	email: EmailSchema,
  password: PasswordSchema,
})

export const RegisterSchema = object({
	firstName: NameSchema,
	lastName: NameSchema,
	email: EmailSchema,
	password: PasswordSchema,
	confirmedPassword: PasswordSchema,
})

export const ForgotPasswordSchema = object({
	email: EmailSchema
})

export const ResetPasswordSchema = object({
	password: PasswordSchema,
	newPassword: PasswordSchema,
	confirmPassword: PasswordSchema
})

export const OTPSchema = object({
	otp: string([
		toTrimmed(),
		minLength(6, "otp_min_length"),
		maxLength(6, "otp_max_length"),
		custom((otp) => /^\d+$/.test(otp), "otp_digit_only")
	])
})

export type LoginData = Output<typeof LoginSchema>;
export type RegisterData = Output<typeof RegisterSchema>;
export type ForgotPasswordData = Output<typeof ForgotPasswordSchema>;
export type ResetPasswordData = Output<typeof ResetPasswordSchema>;
export type OTPSchema = Output<typeof OTPSchema>;
