# 🛠️ Backend Infrastructure Deployment Guide

This guide walks you through setting up the backend infrastructure using **Terraform**, pushing your **Docker image** to **AWS ECR**, and updating your **ECS service**.

---

## 🚀 Step 1: Navigate to the Infrastructure Directory

```bash
cd airport-management-server/infra
```

---

## 🔐 Step 2: Configure AWS Credentials

Make sure your AWS CLI is authenticated.

**Option 1: Using AWS CLI**

```bash
aws configure
```

**Option 2: Using Environment Variables**

```bash
export AWS_ACCESS_KEY_ID=your-access-key-id
export AWS_SECRET_ACCESS_KEY=your-secret-access-key
```

---

## 🧱 Step 3: Initialize Terraform

```bash
terraform init
```

---

## ☁️ Step 4: Deploy Infrastructure

```bash
terraform apply
```

Type `yes` to confirm.

Terraform will provision the following resources:

- Amazon ECR Repository  
- Amazon RDS Database  
- Amazon ECS Cluster  
- ECS Task Definitions & Services  
- Target Group  
- Application Load Balancer (ALB)  
- Security Groups

---

## 🐳 Step 5: Build and Push Docker Image to ECR

1. Go to the **Amazon ECR** console and open your newly created repository.
2. Click **"View push commands"**. The commands will look like:

```bash
# Authenticate Docker to your AWS account
aws ecr get-login-password --region your-region | docker login --username AWS --password-stdin <your-account-id>.dkr.ecr.<your-region>.amazonaws.com

# Build your Docker image
docker build -t your-image-name .

# Tag the image for ECR
docker tag your-image-name:latest <your-account-id>.dkr.ecr.<your-region>.amazonaws.com/your-ecr-repo-name

# Push the image to ECR
docker push <your-account-id>.dkr.ecr.<your-region>.amazonaws.com/your-ecr-repo-name
```

> 🔁 Replace placeholders with your actual values (you can copy/paste directly from the ECR console).

---

## 📦 Step 6: Update ECS Service with New Image

1. Go to the **ECS Console**
2. Select your **Cluster**, then the **Service**
3. Click **Update**, leave all settings unchanged
4. Scroll down and enable **"Force new deployment"**
5. Click **Next**, then **Update Service**

✅ This ensures ECS pulls and deploys the latest Docker image.

---

## 🌐 Step 7: Update Frontend with ALB DNS

After Terraform completes, copy the **DNS name** of the **Application Load Balancer** (from Terraform output or AWS Console).

```env
REACT_APP_API_URL=http://your-alb-dns-name
```
