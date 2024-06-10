# Ansi Converter
Library that streamlines working with using Ansi, **regardless** of language, or implementation.

Currently the project works on Kubuntu (which is what I'm running), I have no clue on it's functionality on windows.


## Implementations 
1. [X] Java - OOP   
    - [X] Implement Ansi text functions (color, effects, etc)   
    - [X] Implement Ansi cursor functions    
    - [X] Implement Demo class that demostrates class functionality   
2. [X] Java - Functions (Also the enum solution)
    - [X] Implement Ansi text functions (color, effects, etc)   
    - [X] Implement Ansi cursor functions    
    - [X] Implement Demo class that demostrates class functionality   
    - [X] Moved the files handling the script to a seperate directory for code reuse
3. [ ] Python - Functions   
4. [ ] Windows functionality    
### Notes on implementations
* Other implementations will be put on hold until a seperate program is made using this library.   
* Extra files to use the library as a controller will be put on hold.   
Solutions such as disabling print is complicated, and requires a structure of the code that makes it simple to enable printing, put in the ansi code, and then redisable -- and even by then there's not much purpose.   
* Functions/classes built ontop of the library meant to stream lining, such as an error function, will be done manually by the person using this library. 
That way it can satisfy the unique requirements that I can not predict and implement myself.


&nbsp;


## Problems to look out for
* Getting the current cursor location using the ansi escape sequence is a nightmare.   
Currently the solution is to run a bash script to print the characters, (with echo turned off), then send the output to a file, and the read that file in the program -- with it being all for nothing if the terminal changes size at all.  
If you delete the bash file, then the function will not work.
* Make sure the terminal does not change at all after the cursors postion is stored, other wise it will be entirely wrong when called back.
* With getting the rgb colors, use the decimal values, not the percentage values.
* When coding with enum, don't put enums in enums, this will damage your performance.


&nbsp;


## Installation
1. Download the repo with the following terminal command:   
```bash
git clone https://github.com/AdamFariello/Ansi-Converter.git
```
2. Drag and drop the library into the code project


&nbsp;


## Useful links:
1) Wikipedia -- https://en.wikipedia.org/wiki/ANSI_escape_code   
2) Blog that lists codes and gives examples -- https://prirai.github.io/blogs/ansi-esc/   
3) Above but also how to make your own interpreter -- https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html   
