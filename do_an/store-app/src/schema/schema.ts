import { name, password } from "@/utils/regex";
import {z} from "zod";

export const EmailSchema = z
  .string()
  .trim()
  .email({ message: "invalid_email" })
  .min(6, { message: "email_min_length" })
  .max(50, { message: "email_max_length" })
  .refine((email) => email.endsWith("gmail.com"), {
    message: "email_gmail_only",
  });

export const PasswordSchema = z
  .string()
  .trim()
  .min(6, { message: "password_min_length" })
  .max(50, { message: "password_max_length" })
  .regex(password, { message: "password_invalid" });

export const NameSchema = z
  .string()
  .trim()
  .min(2, { message: "name_min_length" })
  .max(50, { message: "name_max_length" })
  .regex(name, { message: "name_alpha_only" });

export const OTPSchema = z
  .string()
  .min(6, "otp_min_length")
  .max(6, "otp_max_length")
  .regex(/^\d+$/, { message: "otp_digit_only" });
