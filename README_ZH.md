# AndroidPermissionX

[官网介绍](https://developer.android.com/training/permissions/requesting.html)

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

设置弹出框,当```shouldShowRequestPermissionRationale()``` 返回true的时候，即用户已经拒绝了一次，给用户提示一个解释信息，为什么需要这个权限

```
builder.addPermissionRationale("say why need the permission");
```


添加回调方法

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

开始请求权限

```
builder.build().request();

```