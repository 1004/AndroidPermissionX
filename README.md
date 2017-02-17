# AndroidPermissionX

[中文文档](https://github.com/fccaikai/AndroidPermissionX/blob/master/README_ZH.md)

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

add callBack

```
builder.addRequestPermissionsCallBack(new OnRequestPermissionsCallBack() {
                    @Override
                    public void onResult(String[] permissions, int[] grantResults) {
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            Log.d(TAG, "success");
                        } else {
                            Log.d(TAG, "falied");
                        }
                    }
                })
```

request permission

```
builder.build().request();

```