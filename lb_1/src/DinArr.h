#pragma once

#include <iostream>
#include <string>
using namespace std;

template <class T>
class DinArr
{
public:
	DinArr();
	~DinArr();
	int size() { return Size; }
	bool isEmpty() { if (Size == 0) return false; }
	void add(T);
	bool remove(T);
	T removeAt(int);
	bool contains(T);
	int indexOf(T);
	void clear();
	void sort();

	T& operator[](const int ); //вместо get и set

	void insert(int, T); 
	void pop_front();
	void pop_back();


private:
	int Size;
	T* arr;
};



template <class T>
DinArr<T>::DinArr()
{
	Size = 0;
	T* arr = new T[0];

}



template <class T>
DinArr<T>::~DinArr()

{
	clear();
}



template<class T>
void DinArr<T>::add(T data)
{

	T* newArr = new T[Size + 1];
	if(Size)
	{
		for (int i = 0; i < Size; i++) newArr[i] = arr[i];
		
	}
	newArr[Size] = data;
	arr = newArr;
	
	Size++;
	
}



template<class T>
T DinArr<T>::removeAt(int index)
{
	T retur = NULL;
	if (index < 0 || index >= Size)
	{
		cout << "\nWrong index";
		return retur;
		
	}

	T* newArr = new T[--Size];
	for (int i = 0; i < index; i++) newArr[i] = arr[i];
	for (int i = index; i < Size; i++) newArr[i] = arr[i+1];
	retur = arr[index];
	arr = newArr;
	return retur;

}



template<class T>
bool DinArr<T>::remove(T data)
{
	int count = 0;
	int index;
	do
	{
		index = indexOf(data);
		if (index == -1)
		{
			if (count) return false;
			return true;
		}
			
		removeAt(index);
		count += 1;

	} while (true);
}



template<class T>
bool DinArr<T>::contains(T data)
{
	for (int i = 0; i < Size; i++)
	{
		if (arr[i] == data) return true;
	}
	return false;
}



template<class T>
int DinArr<T>::indexOf(T data)
{
	for (int i = 0; i < Size; i++)
	{
		if (arr[i] == data) return i;
	}
	return -1;
}



template<class T>
T& DinArr<T>::operator[](const int index)
{
	return arr[index];
}



template<class T>
void DinArr<T>::pop_front()
{
	removeAt(0);
}



template<class T>
void DinArr<T>::pop_back()
{
	removeAt(Size - 1);
}



template<class T>
void DinArr<T>::clear()
{
	while (Size) pop_front();
}

template<class T>
void DinArr<T>::insert(int index, T data)
{
	T* newArr = new T[Size + 1];

	if (Size)
	{
		for (int i = 0; i < index; i++) newArr[i] = arr[i];
		newArr[index] = data;
		for (int i = index; i < Size; i++) newArr[i + 1] = arr[i];

	}
	arr = newArr;

	Size++;
}

template<class T>
void DinArr<T>::sort()
{
	if (Size == 1) arr[0] = arr[0];
	else if (Size == 0) cout << "Array Empty" << endl;
	else
	{
		int temp;
		for (int i = 0; i < Size - 1; i++)
		{
			for (int j = 0; j < Size - i - 1; j++)
			{
				if (arr[j] > arr[j + 1])
				{
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
}


//DinArr<int> ar;
//ar.add(4);
//ar.add(3);
//ar.add(1);
//ar.add(9);
//ar.add(1);
//ar.add(1);
//ar.removeAt(1);
//ar.remove(1); 
//for (int i = 0; i < ar.size(); i++) cout << ar[i] << " "; //4 9
//cout << "\n" << ar.contains(9) << " " << ar.contains(1) << endl; //1 0
//cout << ar.indexOf(5) << " " << ar.indexOf(9) << endl; //-1 1 (при неверном индексе indexOf возвр. -1)
//ar.add(8);
//ar.add(14);
//ar.sort();
//for (int i = 0; i < ar.size(); i++) cout << ar[i] << " "; //4 8 9 14
//ar.clear();
//ar.removeAt(3); // Wrong index
//cout << "\n" << ar.size(); // 0

