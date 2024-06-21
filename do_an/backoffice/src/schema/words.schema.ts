import {z} from 'zod';

export const FilterSchema = z.object({
	wordId: z.string(),
	reviseCount: z.number(),
	averageScore: z.number(),
	reviseOn: z.date(),
	createdBy: z.date(),
	updatedBy: z.date(),
})
export type FilterData = z.infer<typeof FilterSchema>;

const TxtSchema = z.object({
	Color: z.string(),
	IsBold: z.string(),
	IsItalic: z.string(),
	IsUnderline: z.string(),
	Font: z.string(),
	FontSize: z.string(),
	Str: z.string(),
})

const DefinitionSchema = z.object({
	DefNumber: z.number(),
	AltForms: z.array(z.string()), // join lai la dc
	IrrFormGroup: z.array(z.string()), //v, ved, vPii. join lai la dc
	ComplementFrame: z.string(), //
	ComplementCollocate: z.string(),
	Grammars: z.array(z.string()), // join lai la dc
	Registers: z.array(z.string()), //Formal, Informal, etc. Join lai la dc
	// UsageE: z.string(),
	// UsageV: z.string(),
	StrE: z.string(),
	StrV: z.string(),
	TxtE: TxtSchema,
	Txt: TxtSchema,
	Examples: z.array(z.object({
		Str: z.string(),
	}))
})

export const WordSchema = z.object({
	_id: z.string(),
	id: z.string(),
	head_word: z.string(),
	head_word_vi: z.string(),
	word_class: z.string(),
	context: z.object({
		Pos: z.string(),
		Phonetic: z.array(z.string()),
		AltForms: z.array(z.string()), // join lai la dc
		IrrFormGroup: z.array(z.string()), // join lai la dc
		Registers: z.array(z.string()), //Formal, Informal, etc. Join lai la dc
		Grammars: z.array(z.string()), // join lai la dc
		Variant: z.array(z.string()),
		Meanings: z.array(DefinitionSchema),
		Shortcuts: z.array(z.object({
			Sd: z.string(),
			Definitions: z.array(DefinitionSchema)
		})),
		Idioms: z.array(z.object({
			Id: z.string(),
			Meanings: z.array(DefinitionSchema)
		})),
		PhrasalVerbs: z.array(z.object({
			Pv: z.string(),
			StrE: z.string(),
			StrV: z.string(),
			Meanings: z.array(DefinitionSchema),
			Examples: z.array(z.object({
				Str: z.string(),
				Txt: TxtSchema,
			}))
		})),
		Synonyms: z.array(z.string()),
		Antonyms: z.array(z.string()),
		Family: z.array(z.string()),
	}),
	audio: z.string(),
})
export type WordData = z.infer<typeof WordSchema>;
