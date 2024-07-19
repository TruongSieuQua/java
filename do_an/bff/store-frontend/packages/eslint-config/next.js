const { resolve } = require("node:path");

const project = resolve(process.cwd(), "tsconfig.json");

/** @type {import("eslint").Linter.Config} */
module.exports = {
	parser: "@typescript-eslint/parser",
	parserOptions: {
		parser: "@typescript-eslint/parser"
	},
	plugins: [
		"only-warn",
		"simple-import-sort",
		"@typescript-eslint",
		"tailwindcss",
		"@next/eslint-plugin-next"
	],
	extends: [
    "eslint:recommended",
    "prettier",
    "turbo",
		"plugin:@next/next/recommended",
		"plugin:@typescript-eslint/recommended",
		"plugin:tailwindcss/recommended"
  ],
	rules: {
		"simple-import-sort/imports": "error",
		"simple-import-sort/exports": "error",
		"tailwindcss/no-custom-classname":"off",
		'@next/next/no-duplicate-head': 'off'
	},
  globals: {
    React: true,
    JSX: true,
  },
  env: {
    node: true,
    browser: true,
  },
  settings: {
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
  ],
  overrides: [{ files: ["*.js?(x)", "*.ts?(x)"] }],
};
