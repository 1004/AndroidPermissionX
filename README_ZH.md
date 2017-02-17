# AndroidPermissionX

### 配置

在项目的 ```build.gradle```中，添加：

 ```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

 ```
 
 在app的 ```build.gradle```中，添加：
 
 ```
 dependencies {
	        compile 'com.github.fccaikai:AndroidPermissionX:0.1.0'
 }
 ```

### 使用

创建一个 PermissionCompat.Builder对象

```
	PermissionCompat.Builder builder = new PermissionCompat.Builder(Context);
```

添加要请求的权限数组

```
	builder.addPermissions(new String[]{Manifest.permission.CAMERA});
```

添加回调方法

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

开始请求权限

```
builder.build().request();

```