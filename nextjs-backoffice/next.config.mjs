/** @type {import('next').NextConfig} */

const cspHeader = `
	default-src 'self';
	form-action 'self';
	frame-ancestors 'self';
	upgrade-insecure-requests;
`;

const nextConfig = ({
	images: {
    remotePatterns: [
      {
        protocol: "https",
        hostname: "**",
      },
    ],
  }
});

export default nextConfig;
