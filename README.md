# MenuAPI

Spigot menu library to simplify menu creation

## Using MenuAPI in your plugin

### Add MenuAPI to your build

#### Gradle
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

```groovy
dependencies {
    implementation 'com.github.1Math3w:menu-api:master-SNAPSHOT'
}
```

#### Maven
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
    <groupId>com.github.1Math3w</groupId>
    <artifactId>menu-api</artifactId>
    <version>master-SNAPSHOT</version>
</dependency>
```

*Note: This library must be included within your plugin jar, so you have to use plugin like [shadowJar](https://github.com/johnrengelman/shadow)*

### Set up the MenuAPI in your plugin

```java
@Override
public void onEnable() {
    MenuAPI.setup(this);
}
```

### Creating a new menu

```java
public class YourMenu extends Menu {
    public YourMenu(Player player) {
        super(player, rows);
    }

    @Override
    public String getMenuName() {
        return "Your Menu";
    }

    @Override
    protected void setMenuItems() {
        //Set the menu content here using the setItem methods
    }
}
```

#### Open Menu
To open the menu, create the new instance of your menu and use `Menu#open`

#### Paginated Menu

```java
public class PageMenu extends PaginatedMenu {
    public PageMenu(Player player) {
        super(player, rows);
    }

    @Override
    public int getPreviousSlot() {
        return previousItemSlot;
    }

    @Override
    public int getNextSlot() {
        return nextItemSlot;
    }

    @Override
    public String getMenuName() {
        return "Menu Name";
    }

    @Override
    protected void setMenuItems() {
        switch (getPage()) {
            case 1:
                //Set the content of the 1. page
                break;
            case 2:
                //Set the content of the 2. page
                break;
            ...
        }
        super.setMenuItems();
    }
}
```

### Setting the content of the menu

- To set the content of the menu you need to create the MenuItem.
- The MenuItem consists of the ItemStack and the click actions.
- To set the MenuItem into the menu, use the `Menu#setItem` method in the overridden `Menu#setMenuItems` method

#### Example
```java
    @Override
    protected void setMenuItems() {
        setItem(0, new MenuItem(Material.DIAMOND)
                .setName(ChatColor.GREEN + "Click me")
                .addClickAction((event) -> Bukkit.getLogger().info("Clicked")));
    }
```