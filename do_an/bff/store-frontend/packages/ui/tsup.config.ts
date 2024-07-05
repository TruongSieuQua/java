import { defineConfig } from "tsup";
import cssModulesPlugin from 'esbuild-css-modules-plugin';

export default defineConfig((options) => ({
    // The file we created above that will be the entrypoint to the library.
    entry: [
        "src/index.ts",
        "src/ui/button/Button.tsx"
    ],
    plugins: [cssModulesPlugin()],
    // Enable TypeScript type definitions to be generated in the output.
    // This provides type-definitions to consumers.
    dts: true,
    // Clean the `dist` directory before building.
    // This is useful to ensure the output is only the latest.
    clean: true,
    outDir: "dist",
    format: ["esm"],
    // Minify the output.
    splitting: true,
    treeshake: true,
    minify: !options.watch,
    external: ['react', 'react-dom'],
    ...options
}));