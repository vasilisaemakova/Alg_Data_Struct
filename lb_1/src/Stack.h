#pragma once

#include <iostream>
#include <string>
using namespace std;

template <class T>
class Steck
{
public:
	Steck();
	~Steck();
	bool isEmpty() {
		if (Size == 0) return true;
		else return false;
	}
	void push(T data);
	T pop();
	T peek() { return head->data; }
	

private:
	
	void clear();
	template <class T>
	class Node
	{
	public:
		Node* pNext;
		T data;
		Node(T data = T(), Node* pNext = nullptr)

		{
			this->data = data;
			this->pNext = pNext;


		}
	};
	Node<T>* head;
	int Size;
};



template <class T>
Steck<T>::Steck()
{
	Size = 0;
	head = nullptr;

}


template <class T>
Steck<T>::~Steck()

{
	clear();
}


template<class T>
void Steck<T>::push(T data)
{
	if (Size == 0)
	{
		head = new Node<T>(data);
	}
	else
	{
		head = new Node<T>(data, head);
	}
	Size++;
}


template<class T>
T Steck<T>::pop()
{
	T retur;
	if (isEmpty()) cout << "Empty";
	Node<T>* temp = head;
	retur = head->data;
	head = head->pNext;
	delete temp;
	Size--;
	return retur;
}


template<class T>
void Steck<T>::clear()
{
	while (Size > 0) pop();
}





/*	Steck<int> m;
	cout << m.isEmpty() << endl; //0
	m.push(8);
	if (m.isEmpty()) cout << "Yes" << endl; //Nothing
	m.pop();
	if (m.isEmpty()) cout << "Yes" << endl; // Yes
*/ 