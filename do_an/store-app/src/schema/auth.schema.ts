import {z} from "zod";
import { EmailSchema, NameSchema, PasswordSchema, OTPSchema as CommonOTPSchema } from './schema';

export const LoginSchema = z.object({
	email: EmailSchema,
  password: PasswordSchema,
})

export const RegisterSchema = z.object({
	firstName: NameSchema,
	lastName: NameSchema,
	email: EmailSchema,
	password: PasswordSchema,
	confirmedPassword: PasswordSchema,
})

export const ForgotPasswordSchema = z.object({
	email: EmailSchema
})

export const ResetPasswordSchema = z.object({
	password: PasswordSchema,
	newPassword: PasswordSchema,
	confirmPassword: PasswordSchema
})

export const OTPSchema = z.object({
	otp: CommonOTPSchema
})

export type LoginData = z.infer<typeof LoginSchema>;
export type RegisterData = z.infer<typeof RegisterSchema>;
export type ForgotPasswordData = z.infer<typeof ForgotPasswordSchema>;
export type ResetPasswordData = z.infer<typeof ResetPasswordSchema>;
export type OTPData = z.infer<typeof OTPSchema>;
