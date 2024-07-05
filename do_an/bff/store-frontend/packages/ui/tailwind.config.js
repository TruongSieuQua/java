const sharedConfig = require('tailwind-config/tailwind.config.js')

/** @type {import('tailwindcss').Config} */
module.exports = {
  ...sharedConfig,
  content: [
    "./src/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  plugins: [
    require('daisyui')
  ],
}