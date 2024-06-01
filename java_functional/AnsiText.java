package java_functional;

import java.util.*;


enum Color {
    BLACK("0"), RED("1"), GREEN("2"), YELLOW("3"), BLUE("4"),
    PURPLE("5"), CYAN("6"), WHITE("7"),
    TEXT("3"), HIGHLIGHT("4"), BRIGHTTEXT("9"), BRIGHTHIGHLIGHT("10");

    String id;
    Color (String id) {
        this.id = id;
    }

    //Required if you want Color.{color} to give back a color
    @Override
    public String toString() {
        return id;
    }
}


class AnsiText extends Ansi {
    public AnsiText() {}

    //Inherented functions
    public AnsiText write (String s) {
        System.out.print(CSI + s + "m");
        return this; 
    }
    public AnsiText writeRaw(String s) {
        String tempCSI = "\\u001B" + "[";
        System.out.print(tempCSI + s + "m"); 
        return this;
    }
    public AnsiText reset () {
        System.out.print(END);
        return this;
    }
    public AnsiText resetln () {
        System.out.println(END);
        return new AnsiText();
    }
    public AnsiText print(String s) { System.out.print(s); return this; }
    public AnsiText println(String s) { System.out.println(s); return this; }


    //TODO Figure out if I want to use enum solution or not, still unhappy with it...
    public AnsiText color(String color) {
        return color(color, false, false);
    }
    public AnsiText color(String color, Boolean isHighlight) {
        return color(color, true, false);
    }
    public AnsiText color(String color, Boolean isHighlight, Boolean isBright) {
        StringBuffer sb = new StringBuffer();

        if (isBright && isHighlight) {
            sb.append(Color.BRIGHTHIGHLIGHT);
        } else if (isBright) {
            sb.append(Color.BRIGHTTEXT);
        } else if (isHighlight) {
            sb.append(Color.HIGHLIGHT);
        } else {
            sb.append(Color.TEXT);
        }

        color = color.toLowerCase();
        switch (color) {
            case "black":
                sb.append(Color.BLACK);
                break;
            case "red":
                sb.append(Color.RED);
                break;
            case "green":
                sb.append(Color.GREEN);      
                break;
            case "yellow":
                sb.append(Color.YELLOW);     
                break;
            case "blue":
                sb.append(Color.BLUE);       
                break;
            case "purple":
                sb.append(Color.PURPLE);     
                break;
            case "cyan":
                sb.append(Color.CYAN);       
                break;
            case "white":
                sb.append(Color.WHITE);      
                break;
            default:
                sb.append(Color.BLACK);
        }

        return write(sb.toString());
    }
    
    public AnsiText color(int color) { return color(color, false); }
    public AnsiText color(int color, boolean isHighlight) {
        if (isHighlight) {
            return write("48:5:" + color);
        } else {
            return write("38:5:" + color);
        }
    }

    //The values are based off the decimal values
    public AnsiText color(int r, int g, int b) { return color(r, g, b, false); }
    public AnsiText color(int r, int g, int b, boolean isHighlight) {
        //Using "println" after using the highlight will highlight all textafter 
        if (isHighlight) {
            return write("48;2;" + r + ";" + g + ";" + b);
        } else {
            return write("38;2;" + r + ";" + g + ";" + b);
        }
    }


 
    //Color manipulate
    public AnsiText reverse () { return write("7");  } //reverse swaps color of highlight and foreground
    public AnsiText inverse_off () { return write("27");  }
    public AnsiText conceal () { return write("8");  }
    public AnsiText reveal_off () { return write("28");  }
    public AnsiText crossed_out () { return write("9");  }
    public AnsiText not_crossed_out () { return write("29");  }


    //Text change
    public AnsiText bold() { return write("1");  }
    public AnsiText italic() { return write("3");  } 
    public AnsiText itallic_off () { return write("23");  }
    public AnsiText underline () { return write("4");  }
    public AnsiText underline_off () { return write("24");  }
    

    //Underlining text
    public AnsiText double_underline () { return write("21");  }
    public AnsiText framed () { return write("51");  }
    public AnsiText framed_off () { return write("54");  }
    public AnsiText encircled () { return write("52");  }
    public AnsiText overlined () { return write("53");  } //Not supported...
    public AnsiText overline_off () { return write("55");  }
}