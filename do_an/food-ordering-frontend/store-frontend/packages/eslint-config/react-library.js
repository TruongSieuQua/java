const { resolve } = require("node:path");

const project = resolve(process.cwd(), "tsconfig.json");

/** @type {import("eslint").Linter.Config} */
module.exports = {
  extends: ["eslint:recommended", "prettier", "turbo",
		"plugin:@typescript-eslint/recommended",
		"plugin:tailwindcss/recommended",
		"plugin:react/recommended",
		"plugin:react-hooks/recommended",
		"prettier"
	],
  plugins: [
		"only-warn",
		"simple-import-sort",
		"@typescript-eslint",
		"tailwindcss",
		"react",
		"react-hooks"
	],
	rules: {
		"simple-import-sort/imports": "error",
		"simple-import-sort/exports": "error",
		"tailwindcss/no-custom-classname":"off"
	},
	parserOptions: {
		parser: "@typescript-eslint/parser"
	},
  globals: {
    React: true,
    JSX: true,
  },
  env: {
    node: true,
  },
  settings: {
		react: {
			version: "detect", // Specify the React version you are using
		},
		"import/resolver": {
      typescript: {
        project,
      },
    },
  },
  ignorePatterns: [
    // Ignore dotfiles
    ".*.js",
    "node_modules/",
    "dist/",
  ],
  overrides: [
    {
      files: ["*.js?(x)", "*.ts?(x)"],
    },
  ],
};
