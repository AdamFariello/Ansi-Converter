package java_oop;

import java.util.*;


enum Color {
    BLACK("0"), RED("1"), GREEN("2"), YELLOW("3"), BLUE("4"),
    PURPLE("5"), CYAN("6"), WHITE("7"),
    TEXT("3"), HIGHLIGHT("4"), BRIGHTTEXT("9"), BRIGHTHIGHLIGHT("10");

    String id;
    Color (String id) {
        this.id = id;
    }

    public String text() { return "3"; }
    public String highlight() { return "4"; }
    public String brightText() { return "9" + text(); }
    public String brightHighlight() { return "10" + highlight(); }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return id;
    }
}


class AnsiText extends Ansi {
    

    public AnsiText write (String s) {
        System.out.print(CSI + s);
        return this; 
    }
    public AnsiText reset () {
        System.out.print(END);
        return this;
    }


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
        }

        //TODO: Find a way for this function to smoothly incorporate this
        //      And basically above
        sb.append("m");

        return write(sb.toString());
    }
    
    public AnsiText color8bit(int color) { return color8bit(color, false); }
    public AnsiText color8bit(int color, boolean isHighlight) {
        if (isHighlight) {
            return write("48:5:" + color + "m");
        } else {
            return write("38:5:" + color + "m");
        }
    }

    //TODO: Test this
    //ESC[38;2;⟨r⟩;⟨g⟩;⟨b⟩ m Select RGB foreground color
    public AnsiText color24bit(int r, int g, int b) { return color24bit(r, g, b, false); }
    public AnsiText color24bit(int r, int g, int b, boolean isHighlight) {
        if (isHighlight) {
            return write("48:2:" + r + ";" + g + ";" + b + "m");
        } else {
            return write("38:2:" + r + ";" + g + ";" + b + "m");
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
    public AnsiText overlined () { return write("53");  }
    public AnsiText overline_off () { return write("55");  }

    public AnsiText print(String s) { System.out.print(s); return this; }
    public AnsiText println(String s) { System.out.println(s); return this; }
}