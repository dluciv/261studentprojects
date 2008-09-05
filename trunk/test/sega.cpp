// sega.cpp : Defines the entry point for the console application.
//
#include "stdio.h"
#include "stdafx.h"
#include <conio.h>
#include <string>
#include <iostream>
using namespace std;

int main(int argc, char* argv[])
{
  	string str ;
		cin >> str;
	for (int y = 0; y < str.size(); y++)
  {
     	
	
		cout << str[y] << "\n";
  }
}

