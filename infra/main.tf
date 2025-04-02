provider "aws" {
  region = "ca-central-1"
}

data "aws_vpc" "default" {
  default = true
}

data "aws_subnets" "default" {
  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.default.id]
  }
}

resource "aws_security_group" "rds_sg" {
  name        = "rds_public_sg"
  description = "Allow MySQL access"
  vpc_id      = data.aws_vpc.default.id

  ingress {
    description = "MySQL from the world (TEMP)"
    from_port   = 3306
    to_port     = 3306
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_security_group" "ec2_sg" {
  name        = "ec2_ssh_sg"
  description = "Allow SSH and HTTP"
  vpc_id      = data.aws_vpc.default.id

  ingress {
    description = "HTTP access"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "App access"
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_db_instance" "mysql" {
  identifier         = "airport"
  engine             = "mysql"
  instance_class     = "db.t3.micro"
  allocated_storage  = 20
  username           = "root"
  password           = "Walexsai00"
  publicly_accessible = true
  skip_final_snapshot = true
  vpc_security_group_ids = [aws_security_group.rds_sg.id]
  db_name = "airport"
  
}


resource "aws_ecr_repository" "app_repo" {
  name = "my-app-repo"
}

module "ecsprovider" {
  source         = "./ecsprovider"
  ecr_repo_url   = aws_ecr_repository.app_repo.repository_url
  db_endpoint    = aws_db_instance.mysql.endpoint
  subnets        = data.aws_subnets.default.ids
  ec2_sg_id      = aws_security_group.ec2_sg.id
  vpc_id         = data.aws_vpc.default.id
}