# Dev

## Test DB JDBC URL

`jdbc:h2:file:E:/Downloads/test`

## K4J UI

[K4J UI 地址](http://localhost:8080/doc.html)

图片显示会有问题，在处理图片是，使用`Swagger UI`

## Swagger UI

[Swagger UI 地址](http://localhost:8080/swagger-ui.html)

## OpenApi

[OpenApi 数据地址](http://localhost:8080/v3/api-docs)

# 参考

## WebSocket

[Spring Boot WebSocket 教程](https://blog.csdn.net/qq_48721706/article/details/124995148)

[Virtual Try On](https://blog.csdn.net/chocoboeater/article/details/105717681)

# 模型

## Yolo

```python
from ultralytics import YOLO

# Create a new YOLO model from scratch
model = YOLO('yolov8n.yaml')

# Load a pretrained YOLO model (recommended for training)
model = YOLO('yolov8n.pt')

# Train the model using the 'coco128.yaml' dataset for 3 epochs
results = model.train(data='coco128.yaml', epochs=3)

# Evaluate the model's performance on the validation set
results = model.val()

# Perform object detection on an image using the model
results = model('https://ultralytics.com/images/bus.jpg')

# Export the model to ONNX format
success = model.export(format='onnx')
```

# 提醒

+ 尽量使用`Optional`，在`src/test/java/com/example/template/othertest/OptionalTests.java`中有使用示例
