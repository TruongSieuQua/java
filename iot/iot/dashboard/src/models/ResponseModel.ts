
export type ResponseModel<T> = {
  success: boolean;
  data?: T;
  error?: {message: string};
}
