function extractU<T extends object, U extends object>(obj: T & U): U {
	const extractedU: Partial<U> = {};
	for (const key in obj) {
			if (obj.hasOwnProperty(key)) {
					if (key in obj && key in ({} as U)) {
							extractedU[key as keyof U] = obj[key as keyof U];
					}
			}
	}
	return extractedU as U;
}
