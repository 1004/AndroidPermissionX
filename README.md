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
        compile 'com.github.fccaikai:AndroidPermissionX:0.1.0'
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
                    public void onResult(String[] permissions, int[] grantResults) {
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            Log.d(TAG, "success");
                            //do something
                        } else {
                            Log.d(TAG, "falied");
                        }
                    }
                    
                    @Override
                    public void onAuthorized() {
                        Log.d(TAG, "Authorized");
                        //do something
                    }
                })
```

request permission

```
builder.build().request();

```