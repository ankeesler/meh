# Ant sucks, and so does Eclipse.

CORE_DIR=core
VIEW_DIR=view
GRAPH_DIR=graph

SHELL=sh

SRC_DIRS=$(CORE_DIR) $(VIEW_DIR) $(GRAPH_DIR)
BUILD_DIR=build
BUILD_DIR_CREATED=$(BUILD_DIR)/tuna

MAIN_CLASS=core.MehDriver

$(BUILD_DIR_CREATED):
	mkdir $(BUILD_DIR)
	touch $@

meh: $(patsubst %, %/*.java, $(SRC_DIRS)) | $(BUILD_DIR_CREATED)
	javac $^ -d $(BUILD_DIR)

run-meh: meh
	cd $(BUILD_DIR); java $(MAIN_CLASS)

clean:
	rm -frd $(BUILD_DIR)
