import { defineConfig } from "tsup";

export default defineConfig((options) => ({
    entry: ['src/**/*.{ts,tsx}', '!src/**/*.d.ts'],
    plugins: [],

    clean: true,
    outDir: "dist",
    format: ["esm"],

    // type definition file will be generated in the `dist` directory.
    // dts: true,
    outExtension: () => ({ js: '.js', dts: '.d.ts'}),

    // Minify the output.
    splitting: true,
    treeshake: true,
    minify: !options.watch,
    external: ['react', 'react-dom'],
    ...options
}));
