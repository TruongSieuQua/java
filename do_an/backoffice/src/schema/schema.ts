import { name, password } from "@/utils/regex";
import {
  custom,
  email,
  endsWith,
  maxLength,
  minLength,
  string,
  toTrimmed,
} from "valibot";

export const EmailSchema = string([
  toTrimmed(),
  email("invalid_email"),
  minLength(6, "email_min_length"),
  maxLength(50, "email_max_length"),
  endsWith("gmail.com", "email_gmail_only"),
]);

export const PasswordSchema = string([
  toTrimmed(),
  minLength(6, "password_min_length"),
  maxLength(50, "password_max_length"),
  custom((p) => password.test(p), "weak_password"),
]);

export const NameSchema = string([
  toTrimmed(),
  minLength(2, "name_min_length"),
  maxLength(50, "name_max_length"),
  custom((n) => name.test(n), "name_alpha_only"),
]);
