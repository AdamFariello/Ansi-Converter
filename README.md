# Rainbow Terminal Text
This is a library of files (w/ different solutions) that can convert given text and output a text effected by an ansi effect(s).

## Defintions and Definitionsw
**ansi-arg** -- The ansi argument used to affect the text, cursor, screen, etc.
**ansi-text** -- A string that is affected by an ansi arguments.
**soft reset** -- Resetting the ansi arguments mid print statement.
**hard reset** -- Resetting the ansi arguments after completing the print statement


## Implementations 
[ ] Java - Enums    
[ ] Java - OOP

## Notes
### Java - OOP
1) Don't put ";" before the final letter of the escape sequence
2) If you plan to over write an ansi-text with another, do a hard-reset first to not also overwrite an ansi-arguments
3) Ansi arguments are case sensitivity

## To learn more
https://en.wikipedia.org/wiki/ANSI_escape_code#SGR_(Select_Graphic_Rendition)_parameters
