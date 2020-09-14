import argparse

parser = argparse.ArgumentParser()

parser.add_argument("-x","--x",type=int)
parser.add_argument("-y","--y",type=int)

args = parser.parse_args()

print(args.x+args.y)
