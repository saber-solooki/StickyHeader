# StickyHeader
Sticky header for recyclerView.
For more information please see [this website](https://google.com)

# Download
grab lastest version via Maven:

```xml
<dependency>
  <groupId>com.saber</groupId>
  <artifactId>stickyheader</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```
or Gradle:
```xml
compile 'com.saber:stickyheader:1.0'
```

extend from StickHeaderRecyclerView and add your main data and header data as generic type.
switch on your viewType and bind your viewHolder of your headers and conents. Your headers and contents can be as many as you want.

```java
public class YourRecyclerAdapter extends StickHeaderRecyclerView<CustomerData, HeaderDataImpl> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HeaderDataImpl.HEADER_TYPE_1:
                return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header1_item_recycler, parent, false));
            case HeaderDataImpl.HEADER_TYPE_2:
                return new Header2ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header2_item_recycler, parent, false));
            default:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).bindData(position);
        } else if (holder instanceof HeaderViewHolder){
            ((HeaderViewHolder) holder).bindData(position);
        } else if (holder instanceof Header2ViewHolder){
            ((Header2ViewHolder) holder).bindData(position);
        }
    }

    @Override
    public void bindHeaderData(View header, int headerPosition) {
        // this method is called when your header move and you must not only bind header data in HeaderViewHolder
        //but also bind header data here.
        TextView tv = header.findViewById(R.id.tvHeader);
        tv.setText(String.valueOf(headerPosition / 5));
    }
  }
```

Next you must implement StickyMainData in your main data class:

```java
public class CustomerData implements StickyMainData {
    //...
}
```

Next you must create your header class:

```java
public class HeaderDataImpl implements HeaderData {
    public static final int HEADER_TYPE_1 = 1;
    public static final int HEADER_TYPE_2 = 2;

    private int headerType;
    @LayoutRes
    private final int layoutResource;

    public HeaderDataImpl(int headerType, @LayoutRes int layoutResource) {
        this.layoutResource = layoutResource;
        this.headerType = headerType;
    }

    @LayoutRes
    @Override
    public int getHeaderLayout() {
        //retunr layout of yourHeader
        return layoutResource;
    }

    @Override
    public int getHeaderType() {
        return headerType;
    }
}
```

Now define your recyclerView and atach your adapter to it:

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        setData(adapter);
        //...
}

    private void setData(RecyclerAdapter adapter) {
        HeaderDataImpl headerData1 = new HeaderDataImpl(HeaderDataImpl.HEADER_TYPE_1, R.layout.header1_item_recycler);
        HeaderDataImpl headerData2 = new HeaderDataImpl(HeaderDataImpl.HEADER_TYPE_2, R.layout.header2_item_recycler);

        List<CustomerData> items = new ArrayList<>();
        items.add(new CustomerData());
        items.add(new CustomerData());
        items.add(new CustomerData());
        items.add(new CustomerData());
        adapter.setHeaderAndData(items, headerData1);


        items = new ArrayList<>();
        items.add(new CustomerData());
        items.add(new CustomerData());
        items.add(new CustomerData());
        items.add(new CustomerData());
        adapter.setHeaderAndData(items, headerData2);
    }

```
# Attention

When you extent StickHeaderRecyclerView, you can't override getItemViewType() method anymore. So if you have multi data type you must override getViewType() function and put your code there.

# License

```xml
Copyright 2018 Saber Solooki

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
