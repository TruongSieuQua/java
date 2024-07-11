import withBundleAnalyzer from "@next/bundle-analyzer";

/** @type {import('next').NextConfig} */
const nextConfig = {
	transpilePackages: ['@peonyui'],
};

export default withBundleAnalyzer(nextConfig);
