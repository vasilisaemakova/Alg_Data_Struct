#pragma once

#include <iostream>
#include <string>
using namespace std;

template <class T>
class List
{
public:
	List();
	~List();
	int size() { return Size; } 
	bool isEmpty() { if (Size == 0) return true; }
	bool add(T);
	T get(int);
	void set(int, T);
	bool remove(T);
	T removeAt(int);
	bool contains(T);
	int indexOf(T);
	void clear();
	void insert(T, int);
	T& operator[](const int); //גלוסעמ get ט set
	void sort();

	void view(int beg = 0, int siz = 3)
	{
		for (int i = beg; i <= siz; i++) cout << get(i);
	}
	void push_front(T);
	void pop_front();
	void pop_back();

	
private:
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
List<T>::List()
{
	Size = 0;
	head = nullptr;

}


template <class T>
List<T>::~List()

{
	clear();
}


template<class T>
void List<T>::push_front(T data)
{
	head = new Node<T>(data, head);
	Size++;

}


template<class T>
bool List<T>::add(T data)
{
	//#list>>>>>>>>set:
	//if (contains(data) == true) return false;  


	if (head == nullptr) head = new Node<T>(data);
	else
	{
		Node<T>* current = this->head;
		while (current->pNext != nullptr) { current = current->pNext; }
		current->pNext = new Node<T>(data);
	}
	Size++;
	return true;
}


template<class T>
T List<T>::get(int index)
{
	if (Size != 0)
	{
		int counter = 0;
		Node<T>* current = this->head;
		while (current != nullptr)
		{
			if (counter == index) return current->data;
			current = current->pNext;
			counter++;
		}
	}
	else return NULL;
}

template<class T>
void List<T>::set(int index, T data)
{
	if (Size != 0)
	{
		int counter = 0;
		Node<T>* current = this->head;
		while (current != nullptr)
		{
			if (counter == index) current->data = data;
			current = current->pNext;
			counter++;
		}
	}
}



template<class T>
bool List<T>::remove(T data)
{
	if (Size != 0)
	{
		bool remov = false;
		if (head->data == data)
		{
			pop_front();
			remov = false;
		}
		Node<T>* current = head;
		while (current->pNext != NULL)
		{

			while (current->pNext->data == data)
			{
				Node<T>* toDelete = current->pNext;
				current->pNext = toDelete->pNext;
				delete toDelete;
				Size--;
				remov = true;
				if (current->pNext == NULL) break;
			}
			if (current->pNext != NULL) current = current->pNext;

		}
		return remov;
	}
	else return false;
	
}



template<class T>
bool List<T>::contains(T data)
{
	Node<T>* current = head;
	while (current != NULL) 
	{
		if (current->data == data) return true;
		current = current->pNext; 
	
	}

	return false;
}



template<class T>
int List<T>::indexOf(T data)
{
	int ind = 0;
	Node<T>* current = head;
	while (current != NULL)
	{
		if (current->data == data) return ind;
		ind++;
		current = current->pNext;

	}

	cout << "not find";
}


template<class T>
T List<T>::removeAt(int index)
{
	if (index == 0) pop_front();
	else
	{
		Node<T>* temp = head;
		for (int i = 0; i < index - 1; i++) temp = temp->pNext;
		Node<T>* toDelete = temp->pNext;
		temp->pNext = toDelete->pNext;

		T retur = toDelete->data;
		delete toDelete;
		Size--;
		return retur;
	}
}



template<class T>
T& List<T>::operator[](const int index)
{
	int counter = 0;
	Node<T>* current = this->head;
	while (current != nullptr)
	{
		if (counter == index) return current->data;
		current = current->pNext;
		counter++;
	}

}



template<class T>
void List<T>::pop_front()
{
	Node<T>* temp = head;
	head = head->pNext;
	delete temp;
	Size--;

}



template<class T>
void List<T>::pop_back()
{
	removeAt(Size - 1);
}




template<class T>
void List<T>::clear()
{
	while (Size) pop_front();
}



template<class T>
void List<T>::insert(T data, int index)
{
	if (index == 0) push_front(data);
	else
	{
		Node<T>* temp = head;
		for (int i = 0; i < index - 1; i++) temp = temp->pNext;
		temp->pNext = new Node<T>(data, temp->pNext);

		Size++;
	}

}



template<class T>
void List<T>::sort()
{
	if (Size == 1) int a = 0;
	else if (Size == 0) cout << "List Empty" << endl;
	else
	{
		int temp;
		for (int i = 0; i < Size - 1; i++)
		{
			for (int j = 0; j < Size - i - 1; j++)
			{
				if (get(j) > get(j + 1))
				{
					temp = get(j);
					set(j, get(j + 1));
					set(j + 1, temp);
				}
			}
		}
	}
}

//template<class T>
//void List<T>::view(int siz = Size)
//{
//	for (int i = 0; i < 10; i++)
//	{
//
//	}
//}


//List<int> myList;
//for (int i = 8; i >= 0; i--)
//{
//	myList.add(i);
//	myList.add(10);
//}
//myList.set(2, 111);
//myList.removeAt(5);
//myList.remove(10);
//myList.sort();
//for (int i = 0; i < myList.size(); i++) cout << myList.get(i) << " "; //0 1 2 3 4 5 6 8 111
//myList.clear();
//if (myList.isEmpty())
//{
//	cout << "\nEmpty" << endl;
//	myList.remove(4);
//	myList.get(9);
//	cout << myList.size() << endl; //0
//}
