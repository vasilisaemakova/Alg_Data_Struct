#include <iostream>
#include "List.h"
#include "Stack.h";
#include "DinArr.h";
#include <string.h>
#include <cstdlib>
using namespace std;


List<string> ans;
List<string> s;

void input()
{
	
	string sTemp, lex;
	getline(cin, sTemp);
	for (int i = 0; i < sTemp.size(); i++)
	{
		if (sTemp[i] != ' ')  lex += string({ sTemp[i] });
		else
		{
			s.add(lex);
			lex.clear();
		}
	}
	s.add(lex);
}

bool is_op(string c) {
	return c == "+" || c == "-" || c == "*" || c == "/" || c == "%" || c == "cos" || c == "sin" || c == "^";
}

int priority(string op) {
	 // op == -'+' || op == -'-'
	
	return op == "+" || op == "-" ? 1 : op == "*" || op == "/" || op == "%" ? 2 : op == "sin" || op == "cos"  ? 4 : op == "^" ? 3 : -1;
}



//void process_op(vector<int>& st, char op) {
//	if (op < 0) {
//		int l = st.back();  st.pop_back();
//		switch (-op) {
//		case '+':  st.push_back(l);  break;
//		case '-':  st.push_back(-l);  break;
//		}
//	}
//	else {
//		int r = st.back();  st.pop_back();
//		int l = st.back();  st.pop_back();
//		switch (op) {
//		case '+':  st.push_back(l + r);  break;
//		case '-':  st.push_back(l - r);  break;
//		case '*':  st.push_back(l * r);  break;
//		case '/':  st.push_back(l / r);  break;
//		case '%':  st.push_back(l % r);  break;
//		}
//	}
//}

void calc() {
	Steck<int> st;
	Steck<string> op;

	for (size_t i = 0; i < s.size(); ++i)
		
		if (s[i] == "(") {
			op.push("(");
		}
		else if (s[i] == ")") {
			while (op.peek() != "(")
				ans.add(op.pop());
			op.pop();
			/*while (!op.isEmpty() && )
			{
					
			}*/
		}
		else if (is_op(s[i])) {
			while (!op.isEmpty() && priority(op.peek()) >= priority(s[i]))
			{
				ans.add(op.pop());
			}
					
			op.push(s[i]);
		}
		else {
			ans.add(s[i]);
		}
	while (!op.isEmpty())
	{
		string lastOp = op.pop();
		ans.add(lastOp);
	}
		
}



int main()
{

	/*
	input();
	calc();
	for (int i = 0; i < ans.size(); i++) cout << ans[i];
	*/
	
	DinArr<int> a;
	a.add(5);
	a.add(3);
	a.add(1);
	a.insert(1, 10);

	for (int i = 0; i < a.size(); i++) cout << a[i] << endl;


	//( 5 + 3 ) / 2 * ( 2 + 3 )                -----------------          53+2/23+*
	//8 + sin ( cos ( 9 + 3 ) * 4 ) / 13       -----------------          893+cos4*sin13/+
	//3 ^ 4 + 2 * 3 + 4 / 2 + sin ( 3 + 4 ) + cos ( 3 )  ------------     34^23*+42/+34+sin+3cos+
	//sin ( 3 ^ 4 + 6 / 2 )           ---------------------------         34^62/+sin
	return 0;

}