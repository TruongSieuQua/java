import { cva } from "cva";

interface BadgeProps extends React.HTMLAttributes<HTMLSpanElement> {
  content?: string;
}

export function Badge() {
  const ButtonVariants = cva(
    {
			base: "inline-flex items-center justify-center",
      variants: {
        /* button colors */
        intent: {
          primary: "bg-green-500 hover:bg-green-600",
          secondary: "bg-red-500 hover:bg-red-600",
          default: "bg-gray-500 hover:bg-gray-600",
        },

        /* button sizes */
        size: {
          small: "h-3 p-3 ms-3 text-sm font-medium",
          medium: ["text-base", "py-2", "px-4"],
          large: ["text-lg", "py-4", "px-8"],
        },
        /* button roundness */
        roundness: {
          square: "rounded-none",
          round: "rounded-md",
          pill: "rounded-full",
        },
      },

      // defaults
      defaultVariants: {
        intent: "default",
        size: "medium",
        roundness: "round",
      },
    },
  );

  return (
    <span className="text-blue-800 bg-blue-100 rounded-full dark:bg-blue-900 dark:text-blue-300">
      Badge
    </span>
  );
}
