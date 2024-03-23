import {toTrimmed, endsWith, email, minLength, maxLength, object, Output, string, custom } from 'valibot';

export const LoginSchema = object({
	email: string([
		toTrimmed(),
		email("invalid_email"),
		minLength(6, "email_min_length"),
		maxLength(50, "email_max_length"),
		endsWith("gmail.com", "email_gmail_only"),
	]),

  password: string([
		toTrimmed(),
		minLength(8, "password_min_length"),
		maxLength(50, "password_max_length"),
		custom((password) =>
		/[a-z]/.test(password) && /[A-Z]/.test(password) && /[0-9]/.test(password) && /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/.test(password)
		, "weak_password")]),
})
export type LoginData = Output<typeof LoginSchema>;
