export const defaultNS = 'default';
export const fallbackLng = 'vi'
export const languages = ['vi', 'en']
export const cookieName = 'i18next'

export type Locales = (typeof languages)[number];

export function getOptions (lng = fallbackLng, ns: string | string[] = defaultNS, resources?: Record<string, Record<string, string>>) {
  return {
    // debug: true,
    // preload: languages,
    fallbackLng,
    lng,
    ns,
		resources,
    // backend: {
    //   projectId: '01b2e5e8-6243-47d1-b36f-963dbb8bcae3'
    // }
  }
}
