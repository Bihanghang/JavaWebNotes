- 属性逐渐改变
  - transition: width 2s;
- 透明度
  - opacity
- 图片在文字后面
  - {position:absolute; z-index:-1;}
---
伪类的效果可以通过添加实际的类来实现 
伪元素的效果可以通过添加实际的元素来实现 
- 伪类与伪元素都是用于向选择器加特殊效果
- 伪类与伪元素的本质区别就是是否抽象创造了新元素
- 伪类只要不是互斥可以叠加使用
- 伪元素在一个选择器中只能出现一次，并且只能出现在末尾
- 伪类与伪元素优先级分别与类、标签优先级相同
---
>两边加框框效果
```css
a {  
    position: relative;  
    display: inline-block;  
    outline: none;  
    text-decoration: none;  
    color: #000;  
    font-size: 12px;  
    padding: 5px 10px;  
}  
a:hover::before, a:hover::after { position: absolute; }
a:hover::before { content: "\5B"; left: -20px; }  
a:hover::after { content: "\5D"; right:  -20px; }  
```
---
