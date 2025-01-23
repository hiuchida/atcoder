# Main
TreeSet<String> setに1つづつシフトした文字列を追加する。
setはソート済なので、first()とlast()で先頭と末尾を取得する。
StringBuilderに初めの文字列を入れ、charAtで先頭の文字コードを得て、
deleteで先頭を削除し、appendで先頭の文字コードを追加する。
