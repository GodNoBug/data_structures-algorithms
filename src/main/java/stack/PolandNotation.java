package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 后缀表达式的计算机求值
 * <p>
 * 从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算（次顶元素 和 栈顶元素），并将结果入栈；重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果
 * <p>
 * 例如: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 完成将一个中缀表达式转后缀表达式的功能
        // 1. 1+((2+3)*4)-5 => 转成 1 2 3 + 4 * + 5 -
        // 2.TODO 对Str操作(扫描)不方便,因此现将中缀表达式转成对应的list,再对list进行遍历
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpression(expression);
        System.out.println("中缀表达式对应的List=" + infixExpressionList);
        // 3.TODO 将得到的中缀表达式对应的List => 后缀表达式对应的List
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List=" + parseSuffixExpressionList);
        System.out.println("表达式最终结果为"+calculate(parseSuffixExpressionList));

        // 先定义一个逆波兰表达式
        // (3+4)×5-6 => 3 4 + 5 × 6 -
        // 4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / + =>76
        // 说明: 为了方便,逆波兰表达式的数字和符号使用空格隔开
        //expression = "3 4 + 5 * 6 -";
        // 1.先将"3 4 + 5 × 6 -"  => 放入到ArrayList中
        // 2.将ArrayList传递给一个方法,遍历ArrayList配合栈完成计算
        //List<String> list = getListString(expression);
        //int res =calculate(list);
        //System.out.println("计算的结果是= "+res);

    }

    // 将中缀表达式转成对应的list 1+((2+3)*4)-5
    private static List<String> toInfixExpression(String expression) {
        List<String> list = new ArrayList<>();
        int i = 0; // 指针,用于遍历中缀表达式字符串
        String str;// 对于多位数的拼接工作
        char c;    // 每遍历到一个字符,就放入到c中
        do {
            // 如果c是非数字,就需要加入到list中
            if ((c = expression.charAt(i)) < 48 || (c = expression.charAt(i)) > 57) {
                list.add("" + c);
                i++;
            } else {// 如果是数字,需要考虑多位数的问题
                str = ""; // 先将str置空
                while (i < expression.length() && (c = expression.charAt(i)) >= 48 && (c = expression.charAt(i)) <= 57) {
                    str += c; // 拼接
                    i++;
                }
                list.add(str);
            }
        } while (i < expression.length());
        return list;
    }

    // 将一个逆波兰表达式,依次将数据和运算符 放入到ArrayList中
    public static List<String> getListString(String expression) {
        // 将表达式分割
        String[] split = expression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    /**
     * //将中缀表达  式“1+((2+3)×4)-5”转换为后缀表达式的过 程如下
     * //因此结果为 "1 2 3 + 4 × + 5 –"
     * 大家看到，后缀表达式适合计算式进行运算，但是人却不太容易写出来，尤其是表达式很长的情况下，因此在开发中，我们需要将 中缀表达式转成后缀表达式。
     * <p>
     * 具体步骤如下:
     * 1) 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     * 2) 从左至右扫描中缀表达式；
     * 3) 遇到操作数时，将其压s2；
     * 4) 遇到运算符时，比较其与s1栈顶运算符的优先级：
     * (1)如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     * (2)否则，若优先级比栈顶运算符的高，也将运算符压入s1；小括号不是运算符
     * (3)否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1步骤)与s1中新的栈顶运算符相比较；
     * 5)遇到括号时：
     * (1) 如果是左括号“(”，则直接压入s1
     * (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6)重复步骤2至5，直到表达式的最右边
     * 7)将s1中剩余的运算符依次弹出并压入s2
     * 8)依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     */
    //将得到的中缀表达式对应的List => 后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> list) {
        // 初始化栈:
        Stack<String> s1 = new Stack<>(); // 符号栈
        // Stack<String> s2 =new Stack<>(); // 存放中间结果的栈,因为s2这个栈在整个转换过程中没有pop的操作,而且后面还需要逆序输出,太麻烦.完全可以用ArrayList来替代
        List<String> s2 = new ArrayList<>(); // 存放中间结果
        // 遍历list
        for (String item : list) {
            // 如果是'数',就入栈
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) { // 如果是左括号
                s1.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); // !!!将'('弹出s1栈.消除小括号
            } else { // 操作符
                // 当item的优先级小于等于栈顶的运算符,应该将s1栈顶的运算符弹出并加入到s2中，再次转到(4-1步骤)与s1中新的栈顶运算符相比较
                // 问题:缺少比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // 还需要将item压入到栈中
                s1.push(item);
            }
        }
        // 将s1中剩余的运算符依次退出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2; // 注意因为是存放到List,因此按顺序输出就是对应的后缀表达式对应的List
    }

    //

    /**
     * 完成对逆波兰表达式的运算
     * 1)从左至右扫描，将3和4压入堆栈； 3 4 + 5 × 6 -
     * 2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
     * 3)将5入栈；
     * 4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
     * 5)将6入栈；
     * 6)最后是-运算符，计算出35-6的值，即29，
     */
    public static int calculate(List<String> list) {
        // 创建一个栈,只需要一个栈即可
        Stack<String> stack = new Stack<>();
        for (String str : list) {
            // 这里使用正则表达式取出数
            if (str.matches("\\d+")) { // 匹配到的多位数
                stack.push(str);// 直接入栈
            } else {
                // pop出两个数,并运算,再入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if (str.equals("+")) {
                    res = num1 + num2;
                } else if (str.equals("-")) {
                    res = num2 - num1; // 注意顺序
                } else if (str.equals("*")) {
                    res = num1 * num2;
                } else if (str.equals("/")) {
                    res = num2 / num1; // 注意顺序
                } else {
                    throw new RuntimeException("运算符错误");
                }
                stack.push(res + "");
            }
        }
        // 最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }

}

// 编写一个类 Operation 可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int NUL = 2;
    private static int DIV = 2;

    // 写一个方法,返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = NUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                result = -1;
                break;
        }
        return result;
    }
}

// 前缀
//   从右至左扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，
//用运算符对它们做相应的计算（栈顶元素 和 次顶元素），并将结果入栈；重复上述过程直到
//表达式最左端，最后运算得出的值即为表达式的结果

// 中缀
//  中缀表达式就是常见的运算表达式，如(3+4)×5-6
//  中缀表达式的求值是我们人最熟悉的，但是对计算机来说却不好操作(前面我们讲的案例
//就能看的这个问题)，因此，在计算结果时，往往会将中缀表达式转成其它表达式来操作(一
//般转成后缀表达式.)

// 后缀
//  从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，
//用运算符对它们做相应的计算（次顶元素 和 栈顶元素），并将结果入栈；重复上述过程直到
//表达式最右端，最后运算得出的值即为表达式的结果
