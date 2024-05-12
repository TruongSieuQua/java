import { Output, array, date, number, object, string } from "valibot";

export const FilterSchema = object({
	wordId: string(),
	reviseCount: number(),
	averageScore: number(),
	reviseOn: date(),
	createdBy: date(),
	updatedBy: date(),
})
export type FilterData = Output<typeof FilterSchema>;

const TxtSchema = object({
	Color: string(),
	IsBold: string(),
	IsItalic: string(),
	IsUnderline: string(),
	Font: string(),
	FontSize: string(),
	Str: string(),
})

const DefinitionSchema = object({
	DefNumber: number(),
	AltForms: array(string()), // join lai la dc
	IrrFormGroup: array(string()), //v, ved, vPii. join lai la dc
	ComplementFrame: string(), //
	ComplementCollocate: string(),
	Grammars: array(string()), // join lai la dc
	Registers: array(string()), //Formal, Informal, etc. Join lai la dc
	// UsageE: string(),
	// UsageV: string(),
	StrE: string(),
	StrV: string(),
	TxtE: TxtSchema,
	Txt: TxtSchema,
	Examples: array(object({
		Str: string(),
	}))
})

export const WordSchema = object({
	_id: string(),
	id: string(),
	head_word: string(),
	head_word_vi: string(),
	word_class: string(),
	context: object({
		Pos: string(),
		Phonetic: array(string()),
		AltForms: array(string()), // join lai la dc
		IrrFormGroup: array(string()), // join lai la dc
		Registers: array(string()), //Formal, Informal, etc. Join lai la dc
		Grammars: array(string()), // join lai la dc
		Variant: array(string()),
		Meanings: array(DefinitionSchema),
		Shortcuts: array(object({
			Sd: string(),
			Definitions: array(DefinitionSchema)
		})),
		Idioms: array(object({
			Id: string(),
			Meanings: array(DefinitionSchema)
		})),
		PhrasalVerbs: array(object({
			Pv: string(),
			StrE: string(),
			StrV: string(),
			Meanings: array(DefinitionSchema),
			Examples: array(object({
				Str: string(),
				Txt: TxtSchema,
			}))
		})),
		Synonyms: array(string()),
		Antonyms: array(string()),
		Family: array(string()),
	}),
	audio: string(),
})
export type WordData = Output<typeof WordSchema>;
