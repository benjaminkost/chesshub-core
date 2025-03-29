from datasets import Dataset, Image
import os
from huggingface_hub import notebook_login

# method to create dict object of ground truth values for specific image
# prerequisite: the moves in the ground truth values have to be in the correct move order
def ground_truth_dict_for_image(image_name: str, abs_path_to_ground_truth_file: str):
    res = {}

    file_ground_truth = open(abs_path_to_ground_truth_file, "r")
    str_ground_truth = file_ground_truth.read()

    list_labels = str_ground_truth.split("\n")

    for label in list_labels:
        if image_name in label:
            # the location and the ground truth label are safed in a dict object
            res[label.split(" ")[0]] = label.split(" ")[1]

    return res

def ground_truth_dict_for_all_images(image_dir: str, abs_path_to_ground_truth_file: str):
    res = {}

    list_images = os.listdir(image_dir)

    for image_compl_name in list_images:
        if ".png" in image_compl_name:
            image_name = image_compl_name.split(".")[0]
            temp_dict = ground_truth_dict_for_image(image_name, abs_path_to_ground_truth_file)
            res[image_compl_name] = temp_dict

    return res

def create_dataset_from_dict_sub_img(img_label_dict):
    # List for Dataset
    data = []

    # Transform data
    for main_img, sub_images in img_label_dict.items():
        for sub_img, label in sub_images.items():
            image_path = os.path.join(image_dir, sub_img)
            if os.path.exists(image_path):
                data.append({"image": image_path, "label": label})

    dataset = Dataset.from_list(data).cast_column("image", Image())

    return dataset

def create_dataset_from_dict_main_img(img_label_dict):
    # List for Dataset
    data = []

    # Transform data
    for main_img, sub_images in img_label_dict.items():
        temp_labels = []
        image_path = os.path.join(image_dir, main_img)
        if os.path.exists(image_path):
            for sub_img, label in sub_images.items():
                    temp_labels.append(label)
            data.append({"image": image_path, "labels": temp_labels})

        dataset = Dataset.from_list(data).cast_column("image", Image())

    return dataset

def save_dataset_to_hugging_face(dataset: Dataset, path_to_save_dataset: str, Owner: str, Dataset_name: str):
    dataset.save_to_disk(path_to_save_dataset)

    notebook_login()

    dataset.push_to_hub(Owner+"/"+Dataset_name)

# Paths
image_dir = "../data/raw_data/unprocessed_hcs_data/images"
label_file = "../data/raw_data/unprocessed_hcs_data/training_tags.txt"
path_to_dataset = "../data/datasets/unprocessed_hcs"

# Load Labels regarding there image names
data_dict = ground_truth_dict_for_all_images(image_dir, label_file)

# Create dataset object form dict
dataset = create_dataset_from_dict_main_img(data_dict)

# Save to huggingface
save_dataset_to_hugging_face(dataset, path_to_dataset, "BenjaminKost", "unprocessed_hcs")



