import { defineConfig } from "cypress";

export default defineConfig({
	e2e: {

		baseUrl: 'http://localhost:3000',
		specPattern: '**/src/**/*.cy.ts',
		setupNodeEvents(on, config) {
			// implement node event listeners here
		},

	},
	component: {
		devServer: {
			framework: "next",
			bundler: "webpack",
		},
	},
});
