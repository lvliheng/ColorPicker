
通过RGB获取色值
===

之前写的10进制转换16进制：

```java
private String tenToSixteen(EditText et) {
		if ("".equals(et.getText().toString().trim())) {
			et.startAnimation(mAnimation);
			return "error";
		}
		int value = Integer.parseInt(et.getText().toString().trim());
		String result = "";
		if (value < 0 || value > 255) {
			et.startAnimation(mAnimation);
			return "error";
		} else {
			if (value == 0) {
				return "00";
			} else {
				int first = value / 16;
				int last = 0;
				if (first == 0) {// value<16
					if (value > 0 && value < 10) {
						result = "0" + value;
					} else if (value > 9) {
						result = "0" + numToLetter(value);
					}
				} else {// value>=16
					last = value - first * 16;
					if (last == 0) {// value=16n
						if (first > 0 && first < 10) {
							result = first + "0";
						} else if (first > 9) {
							result = numToLetter(first) + "0";
						}
						return result;
					} else {
						if (first > 0 && first < 10) {
							if (last > 0 && last < 10) {
								result = first + "" + last;
							} else if (last > 9) {
								result = first + numToLetter(last);
							}
						} else if (first > 9) {
							if (last > 0 && last < 10) {
								result = numToLetter(first) + "" + last;
							} else if (last > 9) {
								result = numToLetter(first) + numToLetter(last);
							}
						}
					}
				}
			}
		}
		return result;
	}

	private String numToLetter(int num) {
		return String.valueOf((char) ('a' + (num - 10)));
	}
```

后来看了别人的代码，才发现：
	
```java
	String resultStr = String.format("#%02x%02x%02x", mList.get(0).getProgress(), mList.get(1).getProgress(),          mList.get(2).getProgress());
```

原来还可以这样！！！
	
然后顺便扒一下人家的图...

![2015-02-27 5 38 00](https://cloud.githubusercontent.com/assets/8185812/6425561/95585b84-bf69-11e4-92b6-6e3f0469ab10.png)
	
多谢：
	
https://github.com/4k3R/material-color-picker
	
:blush:


	   
        
