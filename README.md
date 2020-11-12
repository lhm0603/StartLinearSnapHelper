# StartLinearSnapHelper
一个可以让线性RecyclerView的Item居左或者居右吸附对齐的SnapHelper类，集成自 LinearSnapHelper，修改了对齐规则为靠左或者靠右

## 添加依赖

```
implementation 'com.xm.widget:StartLinearSnapHelper:<latest version>'
```

## 使用方式

##### Kotlin

```kotlin
recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)// 其中false表示居左，true表示居右。内部根据该boolean值来决定是居左还是居右
recyclerView.adapter = MyAdapter()
StartLinearSnapHelper().attachToRecyclerView(recyclerView)
```

##### Java

```java
recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));// 其中false表示居左，true表示居右。内部根据该boolean值来决定是居左还是居右
recyclerView.setAdapter(new MyAdapter());
new StartLinearSnapHelper().attachToRecyclerView(recyclerView);
```



## 许可证

```
   Copyright 2020 lhm0603.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```