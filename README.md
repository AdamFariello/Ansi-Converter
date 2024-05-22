# Ansi Converter
Library that streamlines working with using Ansi, **regardless** of language, or implementation.

## Description
The library, (currently Java OOP), is imported into a class, and you use the library's classes to handle creating Ansi escape sequences.

Currently the project works on Kubuntu (which is what I'm running), I have no clue on it's functionality on windows.

### Implementations 
1. [X] Java - OOP   
    - [X] Implement Ansi text functions (color, effects, etc)   
    - [X] Implement Ansi cursor functions    
    - [X] Implement Demo class that demostrates class functionality   
2. [ ] Java - Functions    
3. [ ] Java - Enums    
4. [ ] Python - Functions   
5. [ ] Windows functionality    
...    

Other implementations will be put on hold until a seperate program is made using this library.

### Problems that occured
* Getting the current cursor location using the ansi escape sequence is a nightmare.
Currently the solution is to run a bash script to print the characters, (with echo turned off), then send the output to a file, and the read that file in the program -- with it being all for nothing if the terminal changes size at all.


## Installation
Run in the terminal:   
``git clone https://github.com/AdamFariello/Ansi-Converter.git``
Copy and paste the "java_oop" folder into the program you're working with.


## Useful links:
1) Wikipedia -- https://en.wikipedia.org/wiki/ANSI_escape_code   
2) Blog that lists codes and gives examples -- https://prirai.github.io/blogs/ansi-esc/   
3) Above but also how to make your own interpreter -- https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html   
