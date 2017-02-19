# AndroidPermissionX

[中文文档](https://github.com/fccaikai/AndroidPermissionX/blob/master/README_ZH.md)

[Android Developer](https://developer.android.com/training/permissions/requesting.html)
### Setup

in your application's ```build.gradle```

 ```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

 ```
 
 in your app's ```build.gradle```
 
 ```
 dependencies {
        compile 'com.github.fccaikai:AndroidPermissionX:1.0.0'
 }
 ```

### Usage

create a PermissionCompat.Builder instance

```
PermissionCompat.Builder builder = new PermissionCompat.Builder(Context);
```

add Permissions Array

```
builder.addPermissions(new String[]{Manifest.permission.CAMERA});
```

to show Rationale Dialog when ```shouldShowRequestPermissionRationale()``` retuen true , to explain why need the permission

```
builder.addPermissionRationale("say why need the permission");
```

add [OnRequestPermissionsCallBack](https://github.com/fccaikai/AndroidPermissionX/blob/master/permissionslib/src/main/java/com/kcode/permissionslib/main/OnRequestPermissionsCallBack.java),like:

```
builder.addRequestPermissionsCallBack(new OnRequestPermissionsCallBack() {
                    @Override
                    public void onGrant() {
                        //do something
                    }

                    @Override
                    public void onDenied(String permission) {
                        Log.e(TAG, permission + "Denied");
                    }
                })
```

request permission

```
builder.build().request();

```