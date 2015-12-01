HttpAgent
====

一个针对项目，而不是具体网络请求库的框架。设计统一的网络请求`Request`和响应`Response`接口，只需要针对不同的网络库进行单独实现，即可做到不修改具体的业务逻辑就能替换基础网络库的方案。


## Features
// TODO

## Getting Started
// TODO

## 为什么要使用该库

众多Android开源网络库造成了开发者的选择困难，而项目又在紧张的进行中，开发者没有足够的时间去选择更好的库。怎么办？

HttpAgent 可以避免开发者陷入具体实现中，只需要根据定义好的接口编程，即可在保证项目进度的前提下，进行具体的实现。而实现也非常简单，只要实现抽象方法即可。

## Copyright & License
 
	Copyright 2015 Liu Wenzhu

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	   http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.

