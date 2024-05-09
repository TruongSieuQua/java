export const letter = (function regex() {
  const japaneseRegex = /\u3040-\u30FF\u3400-\u4DBF\u4E00-\u9FFF\uF900-\uFAFF/;
  const koreanRegex = /\uAC00-\uD7AF/;
  const chineseRegex = /\u4E00-\u9FFF/;
  const vietnameseRegex = /\u00C0-\u1EF9/;
  const englishRegex = /a-zA-Z/;
  const italianRegex = /a-zA-Z\u00C0-\u017F/;
  const russianRegex = /а-яА-Я/;

  const letter = new RegExp(
    japaneseRegex.source +
      koreanRegex.source +
      chineseRegex.source +
      vietnameseRegex.source +
      englishRegex.source +
      italianRegex.source +
      russianRegex.source,
  ).source;
  return letter;
})();
export const number = /0-9/;
export const special = /!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?/;
export const name =  new RegExp(`^([${letter}${number}]+[ ])*[${letter}${number}]+$`);
export const password = new RegExp(`^(?=.*[${letter}])(?=.*[${number}])(?=.*${special}).+$`);
