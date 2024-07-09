import { FieldValues, UseFormProps, UseFormReturn, useForm as useHookForm } from "react-hook-form";
import { zodResolver } from '@hookform/resolvers/zod';
import { ZodSchema } from "zod";

interface UseFormPropsWithResolver<TFieldValues extends FieldValues = FieldValues, TContext = any> extends UseFormProps<TFieldValues, TContext> {
	schema: ZodSchema<TFieldValues>;
}
export const useForm = <TFieldValues extends FieldValues = FieldValues, TContext = any, TTransformedValues extends FieldValues | undefined = undefined>({schema, resolver, ...props}: UseFormPropsWithResolver<TFieldValues, TContext>): UseFormReturn<TFieldValues, TContext, TTransformedValues> => {
	return useHookForm<TFieldValues, TContext, TTransformedValues>({
		resolver : resolver || zodResolver(schema),
		...props
	});
};
