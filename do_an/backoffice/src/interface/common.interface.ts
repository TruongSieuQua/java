export interface PageProps{
    params: {
        lng: string;
    };
}
export interface LayoutProps extends PageProps{
	children: React.ReactNode;
}
