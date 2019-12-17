# Android-BottomNavigationView
BottomNavigationView lib for Android as the normal style. You can ajust each item if you need.

## Import



## How to use
Add this view int your layout.xml file.
```
    <com.arjinmc.bottomnavigationview.BottomNavigationView
        android:id="@+id/bottom_navigation_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#b0ffff00"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:tabIconSize="26dp"
        app:tabItemGravity="center"
        app:tabTextColor="@drawable/selector_font" />
```

Add a navigation item.
```
    NavigationItemView naviItem1 = mBottomNavigationView.newItem();
    //set tab name
    naviItem1.setText(getString(R.string.tab_1));
    //set tab id
    naviItem1.setId(R.id.tab_1);
    naviItem1.setIconDrawable(R.drawable.ic_home);
    //set number count
    naviItem1.setNumber(2);
    //add into BottomNavigationView
    //...many attributes....
    mBottomNavigationView.addItem(naviItem1);
```
Add a BottomNavigationView.OnNavigationItemSelectedListener for NavigationItemChange, it will callback the item id you have defined.
```
    mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener());
```

### BottomNavigationView attributes
The attributes effected all children when added. You can use methods of NavigationItemView to change any child.

#### tabItemGravity
The gravity of layout children as NavigationItemView. It's only two values as center or  bottom.

#### tabNumberTextColor
The number text color of NavigationItemView.

#### tabNumberTextSize
The number text size of NavigationItemView.

#### tabNumberBackground
The number text  background of NavigationItemView.

#### tabNumberMarginLeft
The number text margin left of NavigationItemView.

#### tabNumberMarginTop
The number text margin top of NavigationItemView.

#### tabIconSize
The icon size of NavigationItemView.

#### tabTextColor  
The title text color of NavigationItemView.

#### tabTextSize
The title text size of NavigationItemView.

#### tabTextSelectedBoldStyle
The title text of NavigationItemView will be bold style when the current navigation item is selected. Default is false.

#### tabDrawablePadding
The margin between title text and icon of NavigationItemView.

#### tabBottomPadding
The bottom padding of NavigationItemView.

### BottomNavigationView methods

#### getCurrentItemGravity() 
Get the item gravity for BottomNavigationView. Values is ITEM_GRAVITY_MODE_CENTER, ITEM_GRAVITY_MODE_BOTTOM.

#### getCurrentSelectedItemId()
Get the selected item id.

#### setOnNavigationItemSelectedListener()
Get the callback for NavigationItemView selected change.

### NavigationItemView methods
You can use setXXXX attributes like parent BottomNavigationView to chage style for very NavigationItemView.

#### setCheck()
Set current NavigationItemView Checked.





